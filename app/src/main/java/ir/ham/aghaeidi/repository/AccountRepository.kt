package ir.ham.aghaeidi.repository

import ir.ham.aghaeidi.dataSource.AccountDataSource
import javax.inject.Inject

interface AccountRepository {
    fun getRemoteAccountName(): String
    fun getLocalAccountName(): String
}

class AccountRepositoryImpl @Inject constructor(
    private val accountLocalDataSource: AccountDataSource,
    private val accountRemoteDataSource: AccountDataSource
) : AccountRepository {
    override fun getRemoteAccountName() = accountRemoteDataSource.getAccountName()

    override fun getLocalAccountName() = accountLocalDataSource.getAccountName()
}
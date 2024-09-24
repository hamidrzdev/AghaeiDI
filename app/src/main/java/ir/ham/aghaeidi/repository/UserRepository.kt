package ir.ham.aghaeidi.repository

import ir.ham.aghaeidi.dataSource.UserDataSource

interface UserRepository {
    fun getRemoteUserName(): String
    fun getLocalUserName(): String
}

class UserRepositoryImpl(
//    private val userLocalDataSource: UserDataSource,
//    private val userRemoteDataSource: UserDataSource
) : UserRepository {
    override fun getRemoteUserName() = "userRemoteDataSource.getUserName()"

    override fun getLocalUserName() = "userLocalDataSource.getUserName()"
}
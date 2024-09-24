package ir.ham.aghaeidi.dataSource

interface AccountDataSource {
    fun getAccountName(): String
}

class AccountRemoteAccountName: AccountDataSource{
    override fun getAccountName() = "Remote user"
}

class AccountLocalAccountName: AccountDataSource{
    override fun getAccountName() = "Local user"
}
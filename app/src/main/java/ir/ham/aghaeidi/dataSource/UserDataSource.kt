package ir.ham.aghaeidi.dataSource

interface UserDataSource {
    fun getUserName(): String
}

class UserRemoteUserName: UserDataSource{
    override fun getUserName() = "Remote user"
}

class UserLocalUserName: UserDataSource{
    override fun getUserName() = "Local user"
}
package ir.ham.aghaeidi


interface UserRepository{
    fun getName():String
}

class UserRepositoryImpl(): UserRepository{
    override fun getName(): String {
        return "Farzad"
    }
}

class UserViewModel(userRepository: UserRepository){
    init {
        userRepository.getName()
    }
}
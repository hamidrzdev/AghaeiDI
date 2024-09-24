package ir.ham.aghaeidi

import ir.ham.aghaeidi.dataSource.UserDataSource
import ir.ham.aghaeidi.dataSource.UserLocalUserName
import ir.ham.aghaeidi.dataSource.UserRemoteUserName
import ir.ham.aghaeidi.repository.UserRepository
import ir.ham.aghaeidi.repository.UserRepositoryImpl
import org.koin.core.module.dsl.binds
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
//    singleOf(::UserRemoteUserName){ binds(listOf(UserDataSource::class)) }
//    singleOf(::UserLocalUserName){ binds(listOf(UserDataSource::class)) }


//    single<UserDataSource>(named("remote")) { UserRemoteUserName() }
//    single<UserDataSource>(named("local")) { UserLocalUserName() }
    single<UserRepository> { UserRepositoryImpl() }

//    viewModel { MainViewModel(userRepository = UserRepositoryImpl(userLocalDataSource = UserLocalUserName(), userRemoteDataSource = UserRemoteUserName())) }

    viewModelOf(::MainViewModel)
}

val viewModelModule = module {
}
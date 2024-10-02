package ir.ham.aghaeidi.old

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import ir.ham.aghaeidi.dataSource.AccountDataSource
import ir.ham.aghaeidi.dataSource.AccountLocalAccountName
import ir.ham.aghaeidi.dataSource.AccountRemoteAccountName
import ir.ham.aghaeidi.repository.AccountRepository
import ir.ham.aghaeidi.repository.AccountRepositoryImpl
import ir.ham.aghaeidi.repository.UserRepository
import ir.ham.aghaeidi.repository.UserRepositoryImpl
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Singleton
    @Provides
    @Named("local")
    fun provideLocalAccountDataSource():AccountDataSource{
        return AccountLocalAccountName()
    }
    @Singleton
    @Provides
    @Named("remote")
    fun provideRemoteAccountDataSource():AccountDataSource{
        return AccountRemoteAccountName()
    }
    @Singleton
    @Provides
    fun provideAccountRepository(
        @Named("local") accountLocalUserName: AccountDataSource,
        @Named("remote") accountRemoteUserName: AccountDataSource,
    ):AccountRepository{
        return AccountRepositoryImpl(
            accountLocalDataSource = accountLocalUserName,
            accountRemoteDataSource = accountRemoteUserName
        )
    }
}

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule{
    @Provides
    fun provideUserRepository():UserRepository{
        return UserRepositoryImpl()
    }
}











































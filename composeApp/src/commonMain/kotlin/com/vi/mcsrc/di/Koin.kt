package com.vi.mcsrc.di

import com.vi.mcsrc.di.DataModule
import com.vi.mcsrc.screens.ViewModelModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.KoinApplication
import org.koin.core.annotation.Module

@Module(includes = [DataModule::class, ViewModelModule::class])
@ComponentScan
@Configuration
class AppModule

@KoinApplication
object KoinApp
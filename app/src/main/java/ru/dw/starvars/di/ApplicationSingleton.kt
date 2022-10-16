package ru.dw.starvars.di

import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScopeSingleton {
}
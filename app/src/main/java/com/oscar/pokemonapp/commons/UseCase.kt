package com.oscar.pokemonapp.commons

import com.oscar.pokemonapp.domain.entity.UseCaseException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import com.oscar.pokemonapp.domain.entity.Result

abstract class UseCase<I : UseCase.Request, O : UseCase.Response>(private val configuration: Configuration) {

    fun execute(request: I) = process(request)
        .map {
            Result.Success(it) as Result<O>
        }
        .flowOn(configuration.dispatcher)
        .catch {
            emit(Result.Error(UseCaseException.createFromThrowable(it)))
        }

    internal abstract fun process(request: I): Flow<O>


    class Configuration(val dispatcher: CoroutineDispatcher)

    interface Request

    interface Response
}
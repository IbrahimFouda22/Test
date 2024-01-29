package com.example.domain.exception

open class AppException(error:String?) :Exception(error)
class ServerException (error: String?):AppException(error)
class InternalServerException (error: String?):AppException(error)
class NoInternetException (error: String?):AppException(error)
class EmptyDataException (error: String?):AppException(error)
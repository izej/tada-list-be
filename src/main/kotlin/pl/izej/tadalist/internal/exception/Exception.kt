package pl.izej.tadalist.internal.exception

class NotFoundException(msg: String) : Exception(msg)

class BadRequestException(msg: String) : Exception(msg)

class UnauthorizedException(msg: String) : Exception(msg)

class ForbiddenException(msg: String) : Exception(msg)

class ConflictException(msg: String) : Exception(msg)

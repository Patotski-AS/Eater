package com.pas.eater.data.data_sourse.api.helper

import okio.IOException

sealed class RemoteException(message: String): IOException(message) {
    class ClientError(message: String): RemoteException(message)
    class ServerError(message: String): RemoteException(message)
    class NoNetworkError(message: String): RemoteException(message)
    class GenericError(message: String): RemoteException(message)
}

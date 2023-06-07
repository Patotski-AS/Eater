package com.pas.eater.data.mappers

import android.util.Log
import com.pas.eater.data.data_sourse.api.helper.RemoteException
import com.pas.eater.domain.util.ErrorRecord
import com.pas.eater.domain.util.Record

class ErrorMapper() {
    fun <T> mapErrorRecord(e: RemoteException): Record<T> {
        Log.e(ErrorMapper::class.simpleName, e.message.toString())
        val errorRecord: ErrorRecord = when (e) {
            is RemoteException.ClientError -> ErrorRecord.ClientError
            is RemoteException.ServerError -> ErrorRecord.ServerError
            is RemoteException.NoNetworkError -> ErrorRecord.NetworkError
            else -> ErrorRecord.GenericError
        }
        return Record(null, errorRecord)
    }
}

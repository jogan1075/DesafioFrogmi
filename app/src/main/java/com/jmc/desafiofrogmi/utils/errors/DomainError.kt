package com.jmc.desafiofrogmi.utils.errors

import android.content.res.Resources
import cl.multicaja.klapcomercio.common.utils.bases.errors.Translatable
import com.jmc.desafiofrogmi.R


sealed class DomainError : Translatable {
    object ServiceError : DomainError() {
        override fun getStringResource(resources: Resources): String {
            return resources.getString(R.string.generic_service_error)
        }
    }
    object NoConnectionError : DomainError() {
        override fun getStringResource(resources: Resources): String {
            return resources.getString(R.string.no_internet_error)
        }
    }

    object SessionExpired : DomainError() {
        override fun getStringResource(resources: Resources): String {
            return resources.getString(R.string.session_expired_error)
        }
    }

    object WrongCredentials : DomainError() {
        override fun getStringResource(resources: Resources): String {
            return resources.getString(R.string.wrong_credentials_error)
        }
    }
}

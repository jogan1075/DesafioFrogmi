package cl.multicaja.klapcomercio.common.utils.bases.errors

import android.content.res.Resources

interface Translatable {
    fun getStringResource(resources: Resources) : String
}
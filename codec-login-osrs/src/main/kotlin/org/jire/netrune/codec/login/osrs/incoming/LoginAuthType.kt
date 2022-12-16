package org.jire.netrune.codec.login.osrs.incoming

sealed interface LoginAuthType {

    interface Authenticated : LoginAuthType

    interface AuthTrusted : LoginAuthType {
        val code: Int
    }

    interface AuthUntrusted : LoginAuthType {
        val code: Int
    }

    interface Identified : LoginAuthType {
        val identifier: Int
    }

}

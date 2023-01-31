package org.jire.netrune.rsa

import java.lang.foreign.*

object Gmp {

    private val linker = Linker.nativeLinker()
    private val libgmp = SymbolLookup.libraryLookup("gmp", MemorySession.global())

    private val mpz_init = linker.downcallHandle(
        libgmp.lookup("__gmpz_init").get(),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
    )

    fun mpz_init(x: Addressable) {
        mpz_init.invokeExact(x)
    }

    private val mpz_clear = linker.downcallHandle(
        libgmp.lookup("__gmpz_clear").get(),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
    )

    fun mpz_clear(x: Addressable) {
        mpz_clear.invokeExact(x)
    }

    private val mpz_powm = linker.downcallHandle(
        libgmp.lookup("__gmpz_powm").get(),
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS
        )
    )

    fun mpz_powm(rop: Addressable, base: Addressable, exp: Addressable, mod: Addressable) {
        mpz_powm.invokeExact(rop, base, exp, mod)
    }

    private val mpz_set = linker.downcallHandle(
        libgmp.lookup("__gmpz_set").get(),
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS
        )
    )

    fun mpz_set(rop: Addressable, op: Addressable) {
        mpz_set.invokeExact(rop, op)
    }

    private val mpz_set_str = linker.downcallHandle(
        libgmp.lookup("__gmpz_set_str").get(),
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,

            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.JAVA_INT
        )
    )

    fun mpz_set_str(rop: Addressable, str: Addressable, base: Int) {
        mpz_set_str.invokeExact(rop, str, base)
    }

    private val mpz_import = linker.downcallHandle(
        libgmp.lookup("__gmpz_import").get(),
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS.withName("rop"),
            ValueLayout.JAVA_LONG.withName("count"),
            ValueLayout.JAVA_SHORT.withName("order"),
            ValueLayout.JAVA_LONG.withName("size"),
            ValueLayout.JAVA_SHORT.withName("endian"),
            ValueLayout.JAVA_LONG.withName("nails"),
            ValueLayout.ADDRESS.withName("op")
        )
    )

    fun mpz_import(
        rop: Addressable,
        count: Long,
        order: Short,
        size: Long,
        endian: Short,
        nails: Long,
        op: Addressable
    ) {
        mpz_import.invokeExact(rop, count, order, size, endian, nails, op)
    }

    private val mpz_export = linker.downcallHandle(
        libgmp.lookup("__gmpz_export").get(),
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS.withName("rop"),
            ValueLayout.ADDRESS.withName("countp"),
            ValueLayout.JAVA_SHORT.withName("order"),
            ValueLayout.JAVA_LONG.withName("size"),
            ValueLayout.JAVA_SHORT.withName("endian"),
            ValueLayout.JAVA_LONG.withName("nails"),
            ValueLayout.ADDRESS.withName("op")
        )
    )

    fun mpz_export(
        rop: Addressable,
        countp: Addressable,
        order: Short,
        size: Long,
        endian: Short,
        nails: Long,
        op: Addressable
    ) {
        mpz_export.invokeExact(rop, countp, order, size, endian, nails, op)
    }

    private val mpz_neg = linker.downcallHandle(
        libgmp.lookup("__gmpz_neg").get(),
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS
        )
    )

    fun mpz_neg(rop: Addressable, op: Addressable) {
        mpz_neg.invokeExact(rop, op)
    }

    fun mpz_t(bytes: Long = 16) = MemorySession.global().allocate(bytes)

}
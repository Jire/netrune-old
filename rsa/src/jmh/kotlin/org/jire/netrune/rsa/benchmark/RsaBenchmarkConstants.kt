package org.jire.netrune.rsa.benchmark

import java.math.BigInteger

object RsaBenchmarkConstants {

    const val PAYLOAD_SIZE = 500

    const val MODULUS_RADIX = 36

    const val MODULUS_512 =
        "wqrfi021yd29agpzp082yyt8630hqzxtnbxynhkqbynmc9mtzlp7x0dz45o86q08fqagxyp2tjbjip8qehn8ek5x3s5blnjeh8h"
    val modulus512 = BigInteger(MODULUS_512, MODULUS_RADIX)

    const val MODULUS_1024 =
        "lbqhfup4esizn5p2e0nnv63aadj3qb0jzdvkq6w9mo89q507xdlocmqqsq15c1maxkvg5hgd6exgr702iuztlj3vphe18tlxkbqf2vyl7cc4in5n2nquko7dfvc3pm88cbpd0v2hobk67hp6lw0bnq71bjppxobbcb65sv51r5vm9k79lzoigjah6qwd1x9b4rd255"
    val modulus1024 = BigInteger(MODULUS_1024, MODULUS_RADIX)

    const val MODULUS_2048 =
        "1c5y7drm08jxokpugr4ovgc0jsxi7f3ulad104skkjna8bh89avnn46bzaqvg41teq7thkfeotk31749yrq23wfptjr0ovzuzwr0xme3kgx8bg19i2ss67ulsnsqo4qy7b26kkxiunjmx1wflg0ii5xb9zhpqhr2k4vtrfiolmgli7yrhaw0mlf5aoezbheftfvnch0iooc376mrxhgjcinupiqulg4i17qfbn3rfpywisahvnsrubsfuztlegshzd5og5tbsepf7t7qnuyyx9eohgfd0df13syhcqvy7zji8vlaynqnvbuxar0bqp262tdw8eviratfp27db010b9wk88shzvfwm8txceh5zhmjgby9c5k9arr1st7ep259yihnu9n4bbtlj"
    val modulus2048 = BigInteger(MODULUS_2048, MODULUS_RADIX)

    const val PRIVATE_KEY_RADIX = 36

    const val PRIVATE_KEY_512 =
        "nuw8a47jt2yjz6dh3prda1ssvpoxjvmq3fy5tjow8nrz09vpedk1bwdxwlwl9dxrr7es6vqsemm7l6uxrvqieoawzfxt2oc1vhh"
    val privateKey512 = BigInteger(PRIVATE_KEY_512, PRIVATE_KEY_RADIX)

    const val PRIVATE_KEY_1024 =
        "gpria1ybpxvbi2jdsns6i365molo3d3auks6hc32x306oie0ls38ntru0upxfifkle2nkdenmajcn6zlolyppm1mza3jj6zzpruaenecpc5fbpo24ljv8zn0mm9rind7q70bv1svsmiqr3rb5a31ziymx1maxn0568orxpgz2gxeg543dqdxd0bvaonrry5dqdv9hh"
    val privateKey1024 = BigInteger(PRIVATE_KEY_1024, PRIVATE_KEY_RADIX)

    const val PRIVATE_KEY_2048 =
        "t7wes7hq2vbgxs6izzo6pznklkb0go929hs7r8tq3z8szvepvscfv8s7y4qbidk7vt1bfzzxhiecd5hr8dtpqq38iobh660nzqqhuyn6ysb62ksyjldeuab82m3owab1pd25rpr7tnmh5bl683lpuouohnple1alyrb1h3vi94p21kjgdopupu4962bcmouzt59e32dfhgv7f2lhwtdsjaiempqe94v6mry2lbkzq9gaps0k1i8rewi80cahwko44tcqft98lwo7jn3oeards3dkondan78f5hx6vodelcop8kovsdkh69nuuwwvs4yedskte1sx3ux588w10q5ftudg1nl50j34rba6zvmu3ei3zalknwk4xpzym1cq1vti9jmgvwjs3hwh"
    val privateKey2048 = BigInteger(PRIVATE_KEY_2048, PRIVATE_KEY_RADIX)

}
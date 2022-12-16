package org.jire.netrune.codec.login.osrs.incoming

interface HardwareInfo {

    val cpuFeatures: IntArray
    val osId: Int
    val osVersion: Int
    val javaVendorId: Int
    val javaVersionMajor: Int
    val javaVersionMinor: Int
    val javaVersionUpdate: Int
    val heap: Int
    val logicalProcessors: Int
    val physicalMemory: Int
    val clockSpeed: Int
    val graphicCardReleaseMonth: Int
    val graphicCardReleaseYear: Int
    val cpuCount: Int
    val cpuBrandType: Int
    val cpuModel: Int
    val graphicCardManufacture: String?
    val graphicCardName: String?
    val dxVersion: String?
    val cpuManufacture: String?
    val cpuName: String?
    val isArch64Bit: Boolean
    val isApplet: Boolean

}

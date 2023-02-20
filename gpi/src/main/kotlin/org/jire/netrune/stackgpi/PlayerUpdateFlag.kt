package org.jire.netrune.stackgpi

enum class PlayerUpdateFlag(
    val mask: Int
) {

    Appearance(64),
    SpotAnim(8192),
    Animation(8),
    Say(2),
    FaceEntity(16),
    FaceCoord(1),
    Hit(32),
    MoveSpeed(1024),
    ExactMove(16384),
    TempMoveSpeed(4096),
    NameTag(512),
    Chat(128),
    Tinting(2048);

}
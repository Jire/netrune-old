package org.jire.netrune.codec.game.osrs.outgoing

interface MessageGame : OsrsGameOutPacket {

    val message: String

    val targetPlayerName: String?

    val type: MessageType

    @Suppress("unused")
    enum class MessageType(val id: Int) {
        Unfilterable(0),
        ModChat(1),
        PublicChat(2),
        PrivateChat(3),
        Engine(4),
        LoginLogoutNotification(5),
        PrivateChatOut(6),
        ModPrivateChat(7),
        FriendsChat(9),
        FriendsChatNotification(11),
        TradeSent(12),
        Broadcast(14),
        SnapshotFeedback(26),
        ExamineItem(27),
        ExamineNpc(28),
        ExamineObject(29),
        FriendNotification(30),
        IgnoreNotification(31),
        ClanChat(41),
        ClanMessage(43),
        ClanGuestChat(44),
        ClanGuestMessage(46),
        AutoTyper(90),
        ModAutoTyper(91),
        Console(99),
        TradeRequest(101),
        Trade(102),
        ChallengeRequest(103),
        ChallengeRequestFriendsChat(104),
        Filterable(105),
        PlayerRelated(106),
        TenSecondTimeout(107),
        Welcome(108),
        ClanCreationInvitation(109),
        ClanWarsChallenge(110),
        ClanGimFormGroup(111),
        ClanGimGroupWith(112);
    }

}

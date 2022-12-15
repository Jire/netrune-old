package org.jire.netrune.codec.osrs.game.v209;

enum class ClientProt209(
    val opcode: Int,
    val size: Int
) {

    NoTimeout(0, 0),
    Teleport(1, 9),
    OpHeldT(2, 14),
    ClanChannelKickUser(3, -1),
    FriendListDel(4, -1),
    OpPlayer3(5, 3),
    IfButton10(6, 8),
    CloseModal(7, 0),
    EventKeyboard(8, -2),
    OpNpc5(9, 3),
    ClanJoinChatLeaveChat(10, -1),
    OpLoc3(11, 7),
    IfButton3(12, 8),
    MoveMinimapClick(13, -1),
    OpLoc4(14, 7),
    OpObj4(15, 7),
    IfButton1(16, 8),
    OpNpc3(17, 3),
    OpObjU(18, 15),
    IfButton4(19, 8),
    IfButton5(20, 8),
    OpHeld5(21, 8),
    IfButton9(22, 8),
    IfButton8(23, 8),
    OpObj3(24, 7),
    OpLoc5(25, 7),
    UpdatePlayerModel(26, 13),
    DetectModifiedClient(27, 4),
    OpHeld1(28, 8),
    Timings(29, -1),
    OpObj5(30, 7),
    SetChatFilterSettings(31, 3),
    OpObj2(32, 7),
    ClickWorldMap(33, 4),
    ReflectionCheckReply(34, -1),
    U35(35, -1),
    ResumePCountDialog(36, 4),
    IfButton2(37, 8),
    MessagePrivate(38, -2),
    EventAppletFocus(39, 1),
    FriendListAdd(40, -1),
    ClientCheat(41, -1),
    OpPlayer6(42, 3),
    OpLocE(43, 2),
    OpObjE(44, 6),
    OpPlayer2(45, 3),
    OculusLeave(46, 0),
    OpInv1(47, 8),
    OpObjT(48, 15),
    U49(49, -1),
    OpPlayer7(50, 3),
    WindowStatus(51, 5),
    IfButton6(52, 8),
    EventCameraPosition(53, 4),
    OpLocT(54, 15),
    EventMouseClick(55, 6),
    OpPlayer8(56, 3),
    OpHeldU(57, 16),
    IfButtonT(58, 16),
    OpInv3(59, 8),
    EventMouseMove(60, -1),
    OpNpc1(61, 3),
    U62(62, 7),
    IgnoreListDel(63, -1),
    OpInv4(64, 8),
    OpHeld2(65, 8),
    ResumePNameDialog(66, -1),
    OpNpc4(67, 3),
    OpHeld4(68, 8),
    BugReport(69, -2),
    ResumePauseButton(70, 6),
    OpPlayer1(71, 3),
    OpNpcU(72, 11),
    SendSnapshot(73, -1),
    OpHeld3(74, 8),
    AffinedClanSettingsAddBannedFromChannel(75, -1),
    MessagePublic(76, -1),
    IfButton7(77, 8),
    FriendSetRank(78, -1),
    ClanKickUser(79, -1),
    OpNpcT(80, 11),
    OpInv2(81, 8),
    OpLoc1(82, 7),
    OpNpc2(83, 3),
    OpLocU(84, 15),
    ResumePObjDialog(85, 2),
    OpInvD(86, 9),
    OpNpcE(87, 2),
    Idle(88, 0),
    IgnoreListAdd(89, -1),
    IfButton(90, 4),
    OpInv5(91, 8),
    OpPlayer4(92, 3),
    OpLoc2(93, 7),
    AffinedClanSettingsSetMutedFromChannel(94, -1),
    MoveGameClick(95, -1),
    U96(96, -1),
    MapBuildComplete(97, 0),
    ResumePStringDialog(98, -1),
    MembershipTrialEligibility(99, 2),
    OpHeldE(100, 2),
    SendPingReply(101, 10),
    OpPlayer5(102, 3),
    IfButtonD(103, 16),
    OpPlayerU(104, 11),
    OpObj1(105, 7),
    OpPlayerT(106, 11),
    CrmViewClick(107, 22);

    companion object {
        private val prots = arrayOfNulls<ClientProt209>(256)

        operator fun get(opcode: Int): ClientProt209 =
            prots[opcode] ?: throw IllegalStateException("Invalid opcode: $opcode")

        fun getSize(opcode: Int): Int = prots[opcode]?.size ?: throw IllegalStateException("Illegal opcode: $opcode")

        init {
            for (prot in values()) {
                check(prots[prot.opcode] == null) { "Prot ${prot.opcode} already registered." }
                prots[prot.opcode] = prot
            }
        }
    }

}
package org.jire.netrune.codec.login.osrs.outgoing

sealed interface LoginResponse : OsrsLoginOutPacket {

    interface InvalidCredentials : LoginResponse
    interface Banned : LoginResponse
    interface AlreadyOnline : LoginResponse
    interface ServerUpdated : LoginResponse
    interface WorldFull : LoginResponse
    interface LoginServerOffline : LoginResponse
    interface TooManyConnections : LoginResponse
    interface BadSessionId : LoginResponse
    interface CredentialsInsecure : LoginResponse
    interface MembersWorld : LoginResponse
    interface LoginIncomplete : LoginResponse
    interface UpdateInProgress : LoginResponse
    interface LoginExceeded : LoginResponse
    interface InMembersArea : LoginResponse
    interface AccountLocked : LoginResponse
    interface ClosedBeta : LoginResponse
    interface InvalidLoginServerRequest : LoginResponse
    interface MalformedLoginPacket : LoginResponse
    interface NoReplyFromLoginServer : LoginResponse
    interface ErrorLoadingProfile : LoginResponse
    interface UnexpectedLoginServerResponse : LoginResponse
    interface ComputerAddressBlocked : LoginResponse
    interface ServiceUnavailable : LoginResponse
    interface MustSetDisplayName : LoginResponse
    interface AccountBillingIssue : LoginResponse
    interface AccountInaccessible : LoginResponse
    interface MustVoteFirst : LoginResponse
    interface ClientUpdated : LoginResponse
    interface AccountDoesNotExist : LoginResponse
    interface AccountIneligiblePrivacyPolicy : LoginResponse
    interface PromptAuthenticator : LoginResponse
    interface WrongAuthenticatorCode : LoginResponse
    interface PromptDateOfBirth : LoginResponse
    interface LoginAttemptTimedOut : LoginResponse
    interface YouWereSignedOut : LoginResponse
    interface FailedLoginTryAgain : LoginResponse
    interface FailedLoginTryAgain2 : LoginResponse
    interface MobileClientUpdated : LoginResponse
    interface ProblemUpdatingDateOfBirth : LoginResponse
    interface OpenDateOfBirthWebsite : LoginResponse
    interface DateOfBirthUnderReview : LoginResponse

}

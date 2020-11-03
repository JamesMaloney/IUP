package com.ru.spm.iup_spm;

public class JoinParty {
    private String ParticipantKennitala;
    private String ParticipantName;
    private String PartyPassword;

    public String getParticipantKennitala() {
        return ParticipantKennitala;
    }

    public void setParticipantKennitala(String participantKennitala) {
        ParticipantKennitala = participantKennitala;
    }

    public String getParticipantName() {
        return ParticipantName;
    }

    public void setParticipantName(String participantName) {
        ParticipantName = participantName;
    }

    public String getPartyPassword() {
        return PartyPassword;
    }

    public void setPartyPassword(String partyPassword) {
        PartyPassword = partyPassword;
    }

    public JoinParty(String participantKennitala, String participantName, String partyPassword) {
        ParticipantKennitala = participantKennitala;
        ParticipantName = participantName;
        PartyPassword = partyPassword;
    }
}

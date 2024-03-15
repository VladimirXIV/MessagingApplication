package just.education.convo_messaging_app.service;

import just.education.convo_messaging_app.entity.SessionData;
import just.education.convo_messaging_app.cache.SessionDataCache;


public class SessionDataService {

    private SessionDataCache sessionDataCache;


    public SessionDataService(SessionDataCache sessionDataCache) {
        this.sessionDataCache = sessionDataCache;
    }


    public SessionData get(String wsSessionId) {

        return this.sessionDataCache.get(wsSessionId);

    }

    public SessionData getByParticipantId(final String participantId) {

        return this.sessionDataCache.getByParticipantId(participantId);
    }

    public void put(SessionData sessionData) {

        this.sessionDataCache.put(sessionData);
    }

    public SessionData delete(String wsSessionId) {

        return this.sessionDataCache.delete(wsSessionId);
    }
}
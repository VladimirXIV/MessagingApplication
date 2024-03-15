package just.education.convo_messaging_app.cache;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import just.education.convo_messaging_app.entity.SessionData;


public class SessionDataCache {

    private static final String KEY = "SESSION_DETAIL_";
    private final RedisTemplate<Object, Object> redisTemplate;


    public SessionDataCache(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public void put(final SessionData sessionData) {

        this.redisTemplate.opsForHash().put(KEY, sessionData.getWsSessionId(), "sessionData");
    }

    public SessionData get(String wsSessionId) {

        return (SessionData) this.redisTemplate.opsForHash().get(KEY, wsSessionId);
    }

    public SessionData getByParticipantId(String participantId) {

        // FT.SEARCH idx:participantid "@participantid:144"
        //DefaultRedisScript<SessionData> redisScript = new DefaultRedisScript<>();
        //redisTemplate.execute(redisScript, )

        return (SessionData) this.redisTemplate.opsForHash().get(KEY, participantId);
    }

    public Map<String, SessionData> getAll() {

        Map<Object, Object> entries = this.redisTemplate.opsForHash().entries(KEY);

        return entries.entrySet()
                .stream()
                .map(entry -> new AbstractMap.SimpleEntry<>((String) entry.getKey(), (SessionData) entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public long size() {

        return this.redisTemplate.opsForHash().size(KEY);
    }

    public SessionData delete(final String wsSessionId) {

        final SessionData sessionData = (SessionData) this.redisTemplate.opsForHash().get(KEY, wsSessionId);
        this.redisTemplate.opsForHash().delete(KEY, wsSessionId);
        return sessionData;
    }
}
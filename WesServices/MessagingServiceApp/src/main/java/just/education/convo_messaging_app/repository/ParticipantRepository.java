package just.education.convo_messaging_app.repository;

import org.bson.Document;
import com.mongodb.client.result.UpdateResult;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import just.education.convo_messaging_app.entity.Participant;

public class ParticipantRepository {

    private MongoTemplate mongoTemplate;


    public ParticipantRepository() {
    }

    public ParticipantRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public Participant create(Participant participant) {

        return mongoTemplate.insert(participant);
    };

    public Participant retrieveById(String id) {

        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Participant.class);
    };

    public Participant retrieveByUserId(String userId) {

        Query query = new Query(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, Participant.class);
    };

    public Participant update(Participant participant) {

        Query query = new Query(Criteria.where("id").is(participant.getId()));

        Document document = new Document();
        mongoTemplate.getConverter().write(participant, document);
        Update update = Update.fromDocument(document);

        UpdateResult result = mongoTemplate.upsert(query, update, Participant.class);
        if (result.wasAcknowledged()) {
            return mongoTemplate.findOne(query, Participant.class);
        } else {
            return null;
        }
    };

    public Participant deleteById(String id) {

        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findAndRemove(query, Participant.class);
    };
}
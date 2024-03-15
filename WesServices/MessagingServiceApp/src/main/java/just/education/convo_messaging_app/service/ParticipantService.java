package just.education.convo_messaging_app.service;

import java.util.Date;
import java.time.Instant;

import just.education.convo_messaging_app.entity.Participant;
import just.education.convo_messaging_app.dto.ParticipantReadDto;
import just.education.convo_messaging_app.dto.ParticipantUpdateDto;
import just.education.convo_messaging_app.dto.ParticipantCreateDto;
import just.education.convo_messaging_app.mapper.ParticipantMapper;
import just.education.convo_messaging_app.repository.ParticipantRepository;


public class ParticipantService {

    private ParticipantRepository participantRepository;
    private ParticipantMapper participantMapper;


    public ParticipantService() {
    }

    public ParticipantService(ParticipantRepository participantRepository, ParticipantMapper participantMapper) {
        this.participantRepository = participantRepository;
        this.participantMapper = participantMapper;
    }


    public ParticipantReadDto create(final ParticipantCreateDto participantCreateDto) {

        final Participant participant = this.participantMapper.toParticipant(participantCreateDto);

        final Date currentDate = Date.from(Instant.now());
        participant.setEnteredAt(currentDate);

        final Participant createdParticipant = this.participantRepository.create(participant);

        return this.participantMapper.toParticipantReadDto(createdParticipant);
    }
    public ParticipantReadDto update(final String id, final ParticipantUpdateDto participantUpdateDto) {

        final Participant participant = this.participantRepository.retrieveById(id);

        this.participantMapper.update(participantUpdateDto, participant);
        final Participant updatedParticipant = this.participantRepository.update(participant);

        return this.participantMapper.toParticipantReadDto(updatedParticipant);
    }

    public ParticipantReadDto findById(final String id) {

        final Participant participant = this.participantRepository.retrieveById(id);

        return this.participantMapper.toParticipantReadDto(participant);
    }

    public ParticipantReadDto deleteById(final String id) {

        final Participant participant = this.participantRepository.retrieveById(id);

        return this.participantMapper.toParticipantReadDto(participant);
    }

    public ParticipantReadDto deleteByUserId(final String userId) {

        final Participant participant = this.participantRepository.retrieveByUserId(userId);

        return this.participantMapper.toParticipantReadDto(participant);
    }
}

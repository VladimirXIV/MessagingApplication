package just.education.group_messaging_app.config;

import just.education.group_messaging_app.entity.Post;
import just.education.group_messaging_app.entity.Group;
import just.education.group_messaging_app.entity.Member;
import just.education.group_messaging_app.entity.Message;
import just.education.group_messaging_app.entity.GroupRole;
import just.education.group_messaging_app.entity.Followship;
import just.education.group_messaging_app.entity.GroupStatus;
import just.education.group_messaging_app.entity.MemberStatus;

import just.education.group_messaging_app.mapper.PostMapper;
import just.education.group_messaging_app.mapper.GroupMapper;
import just.education.group_messaging_app.mapper.MemberMapper;
import just.education.group_messaging_app.mapper.MessageMapper;
import just.education.group_messaging_app.mapper.FollowshipMapper;

import just.education.group_messaging_app.repository.PostRepository;
import just.education.group_messaging_app.repository.GroupRepository;
import just.education.group_messaging_app.repository.MemberRepository;
import just.education.group_messaging_app.repository.MessageRepository;
import just.education.group_messaging_app.repository.GroupRoleRepository;
import just.education.group_messaging_app.repository.FollowshipRepository;
import just.education.group_messaging_app.repository.GroupStatusRepository;
import just.education.group_messaging_app.repository.MemberStatusRepository;

import just.education.group_messaging_app.service.PostService;
import just.education.group_messaging_app.service.GroupService;
import just.education.group_messaging_app.service.MemberService;
import just.education.group_messaging_app.service.MessageService;
import just.education.group_messaging_app.service.FollowshipService;

import just.education.group_messaging_app.serviceimpl.PostServiceimpl;
import just.education.group_messaging_app.serviceimpl.GroupServiceImpl;
import just.education.group_messaging_app.serviceimpl.MemberServiceImpl;
import just.education.group_messaging_app.serviceimpl.MessageServiceImpl;
import just.education.group_messaging_app.serviceimpl.FollowshipServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


@Configuration
public class AppConfig {

    @Bean
    public GroupMapper groupMapper() {

        return GroupMapper.INSTANCE;
    }

    @Bean
    public FollowshipMapper followerMapper() {

        return FollowshipMapper.INSTANCE;
    }

    @Bean
    public MemberMapper memberMapper() {

        return MemberMapper.INSTANCE;
    }

    @Bean
    public MessageMapper messageMapper() {

        return MessageMapper.INSTANCE;
    }

    @Bean
    public PostMapper postMapper() {

        return PostMapper.INSTANCE;
    }

    @Bean
    public JpaRepositoryFactoryBean<GroupRepository, Group, Long> groupRepository() {

        return new JpaRepositoryFactoryBean<>(GroupRepository.class);
    }

    @Bean
    public JpaRepositoryFactoryBean<GroupStatusRepository, GroupStatus, Long> groupStatusRepository() {

        return new JpaRepositoryFactoryBean<>(GroupStatusRepository.class);
    }

    @Bean
    public JpaRepositoryFactoryBean<GroupRoleRepository, GroupRole, Long> groupRoleRepository() {

        return new JpaRepositoryFactoryBean<>(GroupRoleRepository.class);
    }

    @Bean
    public JpaRepositoryFactoryBean<FollowshipRepository, Followship, Long> followshipRepository() {

        return new JpaRepositoryFactoryBean<>(FollowshipRepository.class);
    }

    @Bean
    public JpaRepositoryFactoryBean<MemberRepository, Member, Long> memberRepository() {

        return new JpaRepositoryFactoryBean<>(MemberRepository.class);
    }

    @Bean
    public JpaRepositoryFactoryBean<MemberStatusRepository, MemberStatus, Long> memberStatusRepository() {

        return new JpaRepositoryFactoryBean<>(MemberStatusRepository.class);
    }

    @Bean
    public JpaRepositoryFactoryBean<MessageRepository, Message, Long> messageRepository() {

        return new JpaRepositoryFactoryBean<>(MessageRepository.class);
    }

    @Bean
    public JpaRepositoryFactoryBean<PostRepository, Post, Long> postRepository() {

        return new JpaRepositoryFactoryBean<>(PostRepository.class);
    }

    @Bean
    public GroupService groupService(GroupRepository groupRepository, GroupStatusRepository groupStatusRepository, GroupMapper groupMapper) {

        return new GroupServiceImpl(groupRepository, groupStatusRepository, groupMapper);
    }

    @Bean
    public FollowshipService followshipService(FollowshipRepository followshipRepository, GroupRepository groupRepository, FollowshipMapper followshipMapper) {

        return new FollowshipServiceImpl(followshipRepository, groupRepository, followshipMapper);
    }

    @Bean
    public MemberService memberService(MemberRepository memberRepository,
                                       MemberStatusRepository memberStatusRepository,
                                       GroupRoleRepository groupRoleRepository,
                                       GroupRepository groupRepository,
                                       MemberMapper memberMapper) {

        return new MemberServiceImpl(memberRepository, memberStatusRepository, groupRoleRepository, groupRepository, memberMapper);
    }

    @Bean
    public MessageService messageService(MessageRepository messageRepository, GroupRepository groupRepository, MessageMapper messageMapper) {

        return new MessageServiceImpl(messageRepository, groupRepository, messageMapper);
    }

    @Bean
    public PostService postService(PostRepository postRepository, GroupRepository groupRepository, PostMapper postMapper) {

        return new PostServiceimpl(postRepository, groupRepository, postMapper);
    }
}
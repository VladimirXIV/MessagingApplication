package just.education.group_messaging_app.config;

import just.education.group_messaging_app.entity.Post;
import just.education.group_messaging_app.entity.Group;
import just.education.group_messaging_app.entity.Member;
import just.education.group_messaging_app.entity.Message;
import just.education.group_messaging_app.entity.Follower;

import just.education.group_messaging_app.mapper.*;
import just.education.group_messaging_app.repository.*;

import just.education.group_messaging_app.service.*;
import just.education.group_messaging_app.serviceimpl.*;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;


public class AppConfig {

    @Bean
    public GroupMapper groupMapper() {

        return GroupMapper.INSTANCE;
    }

    @Bean
    public FollowerMapper followerMapper() {

        return FollowerMapper.INSTANCE;
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
    public GroupRepository groupRepository() {

        return new JpaRepositoryFactoryBean<>(GroupRepository.class).getObject();
    }

    @Bean
    public FollowerRepository followerRepository() {

        return new JpaRepositoryFactoryBean<>(FollowerRepository.class).getObject();
    }

    @Bean
    public MemberRepository memberRepository() {

        return new JpaRepositoryFactoryBean<>(MemberRepository.class).getObject();
    }

    @Bean
    public MessageRepository messageRepository() {

        return new JpaRepositoryFactoryBean<>(MessageRepository.class).getObject();
    }

    @Bean
    public GroupStatusRepository groupStatusRepository() {

        return new JpaRepositoryFactoryBean<>(GroupStatusRepository.class).getObject();
    }

    @Bean
    public PostRepository postRepository() {

        return new JpaRepositoryFactoryBean<>(PostRepository.class).getObject();
    }

    @Bean
    public GroupService groupService(GroupRepository groupRepository, GroupStatusRepository groupStatusRepository, GroupMapper groupMapper) {

        return new GroupServiceImpl(groupRepository, groupStatusRepository, groupMapper);
    }

    @Bean
    public FollowerService followerService(FollowerRepository followerRepository, FollowerMapper followerMapper) {

        return new FollowerServiceImpl(followerRepository, followerMapper);
    }

    @Bean
    public MemberService memberService(MemberRepository memberRepository, MemberMapper memberMapper) {

        return new MemberServiceImpl(memberRepository, memberMapper);
    }

    @Bean
    public MessageService messageService(MessageRepository messageRepository, MessageMapper messageMapper) {

        return new MessageServiceImpl(messageRepository, messageMapper);
    }

    @Bean
    public PostService postService(PostRepository postRepository, PostMapper postMapper) {

        return new PostServiceimpl(postRepository, postMapper);
    }
}
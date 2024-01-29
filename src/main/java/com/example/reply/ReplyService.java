package com.example.reply;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.error.DataNotFoundException;
import com.example.post.Post;
import com.example.user.SiteUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {

	private final ReplyRepository replyRepository;
	
	public void create(Post post, String content, SiteUser siteUser) {
		Reply reply = new Reply();
		reply.setContent(content);
		reply.setCreatedDate(LocalDateTime.now());
		reply.setPost(post);
		reply.setAuthor(siteUser);
		
		replyRepository.save(reply);
	}

	public Reply getReply(Integer replyId) {
		Optional<Reply> optionalReply = replyRepository.findById(replyId);
		if (optionalReply.isEmpty()) {
			throw new DataNotFoundException("댓글정보가 존재하지 않습니다.");
		}
		
		return optionalReply.get();
	}

	public void modify(Reply reply, String content) {
		reply.setContent(content);
		reply.setUpdatedDate(LocalDateTime.now());
		
		replyRepository.save(reply);
	}
	
	public void delete(Reply reply) {
		replyRepository.delete(reply);
	}
}

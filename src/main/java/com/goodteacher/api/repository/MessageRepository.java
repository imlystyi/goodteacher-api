package com.goodteacher.api.repository;

import com.goodteacher.api.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Repository to manage {@link Message} instances.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Set<Message> findAllByFromUserIdAndToUserId(Long fromUserId, Long toUserId);

}

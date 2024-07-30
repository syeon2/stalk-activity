package io.waterkite94.stalk.application.usecase

import io.waterkite94.stalk.application.port.CommentPersistencePort
import io.waterkite94.stalk.application.port.LikePersistencePort
import io.waterkite94.stalk.application.port.PostPersistencePort
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@ExtendWith(MockitoExtension::class)
abstract class IntegrationTestSupport {
    @Mock
    protected lateinit var postPersistencePort: PostPersistencePort

    @Mock
    protected lateinit var commentPersistencePort: CommentPersistencePort

    @Mock
    protected lateinit var likePersistencePort: LikePersistencePort
}

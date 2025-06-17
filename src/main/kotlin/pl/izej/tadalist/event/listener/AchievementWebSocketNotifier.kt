package pl.izej.tadalist.event.listener

import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component
import pl.izej.tadalist.event.AchievementUnlockedEvent

@Component
class AchievementWebSocketNotifier(
    private val messagingTemplate: SimpMessagingTemplate
) {

    @EventListener
    fun onAchievementUnlocked(event: AchievementUnlockedEvent) {
        val destination = "/topic/achievements/${event.profileId}"
        messagingTemplate.convertAndSend(destination, event)
    }

}

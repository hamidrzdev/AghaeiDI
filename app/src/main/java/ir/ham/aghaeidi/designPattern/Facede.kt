package ir.ham.aghaeidi.designPattern

/******************** MUSIC PLAYER ******************/
class MusicPlayer {
    fun playMusic(track: String) {
        println("Playing music track: $track")
    }

    fun stopMusic() {
        println("Music stopped.")
    }
}


/******************** VIDEO PLAYER ******************/
class VideoPlayer {
    fun playVideo(video: String) {
        println("Playing video: $video")
    }

    fun stopVideo() {
        println("Video stopped.")
    }
}


/******************** MEDIA FACADE ******************/
class MediaFacade {
    private val musicPlayer: MusicPlayer = MusicPlayer()
    private val videoPlayer: VideoPlayer = VideoPlayer()

    fun playMusic(track: String) {
        musicPlayer.playMusic(track)
    }

    fun stopMusic() {
        musicPlayer.stopMusic()
    }

    fun playVideo(video: String) {
        videoPlayer.playVideo(video)
    }

    fun stopVideo() {
        videoPlayer.stopVideo()
    }
}

/******************** MAIN ******************/
fun main() {
    val mediaFacade = MediaFacade()

    // Using the facade to play music and video
    mediaFacade.playMusic("My Favorite Song")
    mediaFacade.playVideo("My Favorite Movie")

    // Stop music and video
    mediaFacade.stopMusic()
    mediaFacade.stopVideo()
}
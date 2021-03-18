package cipherdogs

import java.awt.Color
import java.awt.image.BufferedImage
import java.security.MessageDigest

object Identicons extends App {
  def md5(s: String): Array[Byte] = {
    MessageDigest.getInstance("MD5").digest(s.getBytes)
  }

  if (args.length == 0) throw new RuntimeException("Parameter missing")

  val canvas = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)
  val g      = canvas.getGraphics

  var hash: Array[Byte] = md5(args(0))

  val background = new Color(255, 255, 255)
  val foreground = new Color(hash(0) & 255, hash(1) & 255, hash(2) & 255)

  for (x <- 0 to 1;
       y <- 0 to 4) {
    if ((hash(x + 3) >> y & 1) == 1) {
      g.setColor(foreground)
    } else {
      g.setColor(background)
    }
    g.fillRect(x * 100, y * 100, 100, 100)
    g.fillRect(400 - x * 100, y * 100, 100, 100)
  }

  for (y <- 0 to 4) {
    if ((hash(5) >> y & 1) == 1) {
      g.setColor(foreground)
    } else {
      g.setColor(background)
    }
    g.fillRect(200, y * 100, 100, 100)
  }

  javax.imageio.ImageIO.write(canvas, "png", new java.io.File("drawing.png"))
}

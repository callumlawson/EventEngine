package OldCore

import scala.concurrent.duration.Duration

/**
 * User: Callum
 * Date: 28/09/13
 * Time: 20:36
 */
object Timer {
  def every(time: Duration) (callback: => Unit) {
    while (true) { callback; Thread sleep time.toMillis }
  }
}
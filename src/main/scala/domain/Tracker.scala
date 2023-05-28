package com.jss.notifier
package com.jss.notifier.domain

trait Tracker {
  def track(shipmentIdentifier: String): Unit
}

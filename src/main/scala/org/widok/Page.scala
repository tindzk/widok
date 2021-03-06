package org.widok

trait BasePage {
  def contents(): Seq[Widget]

  def render() {
    (for {
      page <- DOM.element("page")
    } yield {
      DOM.clear(page)

      contents().foreach(elem =>
        page.appendChild(elem.rendered))
    }).orElse {
      sys.error("DOM element not found. The JavaScript files must be loaded at the end of the HTML document.")
    }
  }
}

trait Page extends BasePage {
  def ready(route: InstantiatedRoute)

  def render(route: InstantiatedRoute) {
    render()
    ready(route)
  }
}

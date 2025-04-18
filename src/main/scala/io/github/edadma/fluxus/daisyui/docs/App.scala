package io.github.edadma.fluxus.daisyui.docs

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.daisyui.*
import org.scalajs.dom

@main def run(): Unit =
  render(App, "app")

// Main application entry point
def App: FluxusNode = {
  // Simple hash-based routing
  val (route, setRoute, _) = useState(
    Option(dom.window.location.hash)
      .filter(_.nonEmpty)
      .map(_.substring(1))
      .getOrElse("home"),
  )

  // Listen for hash changes
  useEffect(
    () => {
      val handleHashChange = (_: dom.Event) => {
        val newRoute = Option(dom.window.location.hash)
          .filter(_.nonEmpty)
          .map(_.substring(1))
          .getOrElse("home")

        setRoute(newRoute)
      }

      dom.window.addEventListener("hashchange", handleHashChange)

      () => dom.window.removeEventListener("hashchange", handleHashChange)
    },
    Seq(),
  )

  // Routing logic
  val content = route match {
    case "home"     => HomePage <> ()
    case "overview" => ComponentsOverviewPage <> ()
    case "button"   => ButtonPage <> ()
    case "table"    => TablePage <> ()
    case _          => NotFoundPage <> ()
  }

  // Navigation items for sidebar
  val navItems = List(
    NavItem(
      id = "home",
      title = "Home",
      icon = Some(HomeIcon),
      href = Some("#home"),
      isActive = route == "home",
    ),
    NavItem(
      id = "overview",
      title = "Components Overview",
      icon = Some(GridIcon),
      href = Some("#overview"),
      isActive = route == "overview",
    ),
    NavItem(
      id = "components",
      title = "Components",
      icon = Some(ComponentsIcon),
      items = List(
        NavItem(
          id = "button",
          title = "Button",
          href = Some("#button"),
          isActive = route == "button",
        ),
        NavItem(
          id = "table",
          title = "Table",
          href = Some("#table"),
          isActive = route == "table",
        ),
        // Additional components would be added here
      ),
    ),
    NavItem(
      id = "github",
      title = "GitHub",
      icon = Some(GitHubIcon),
      href = Some("https://github.com/edadma/fluxus-daisyui"),
      onClick = Some(() => ()),
    ),
  )

  // Handle navigation
  def handleNavigation(id: String, item: NavItem): Unit = {
    // External links are handled by the browser
    if (item.href.exists(_.startsWith("http"))) {
      return
    }

    // Update the route based on the item's href
    item.href.foreach { href =>
      dom.window.location.hash = href.substring(1)
    }
  }

  div(
    cls          := "flex h-screen bg-base-100",
    "data_theme" := "light",

    // Sidebar (visible on larger screens)
    div(
      cls := "hidden lg:block",
      Sidebar <> SidebarProps(
        items = navItems,
        variant = "normal",
        bordered = true,
        bgClass = "bg-base-200",
        width = "w-64",
        onNavigation = Some(handleNavigation),
        className = "h-screen",
      ),
    ),

    // Mobile sidebar toggle + Content
    div(
      cls := "flex flex-col flex-1 overflow-hidden",

      // Header (mobile only)
      div(
        cls := "flex h-16 items-center px-4 border-b lg:hidden",

        // Mobile menu button (opens drawer)
        label(
          htmlFor := "mobile-drawer",
          cls     := "btn btn-square btn-ghost drawer-button lg:hidden",
          svg(
            xmlns       := "http://www.w3.org/2000/svg",
            fill        := "none",
            viewBox     := "0 0 24 24",
            strokeWidth := "1.5",
            stroke      := "currentColor",
            cls         := "w-6 h-6",
            path(
              strokeLinecap  := "round",
              strokeLinejoin := "round",
              d              := "M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5",
            ),
          ),
        ),

        // Logo/title
        a(
          href := "#home",
          cls  := "text-xl font-bold ml-4",
          "Fluxus DaisyUI",
        ),

        // GitHub link (mobile)
        div(
          cls := "ml-auto",
          a(
            href   := "https://github.com/edadma/fluxus-daisyui",
            target := "_blank",
            cls    := "btn btn-ghost btn-square",
            GitHubIcon,
          ),
        ),
      ),

      // Main content
      div(
        cls := "flex-1 overflow-y-auto p-6",
        content,
      ),
    ),

    // Mobile drawer
    div(
      cls := "drawer lg:hidden",
      input(
        typ := "checkbox",
        id  := "mobile-drawer",
        cls := "drawer-toggle",
      ),
      div(
        cls := "drawer-side z-40",
        label(
          htmlFor := "mobile-drawer",
          cls     := "drawer-overlay",
        ),
        div(
          Sidebar <> SidebarProps(
            items = navItems,
            variant = "normal",
            bordered = false,
            bgClass = "bg-base-200",
            width = "w-64",
            onNavigation = Some((id, item) => {
              // Close drawer and navigate
              val drawerToggle = dom.document.getElementById("mobile-drawer").asInstanceOf[dom.html.Input]
              drawerToggle.checked = false
              handleNavigation(id, item)
            }),
            className = "h-screen",
          ),
        ),
      ),
    ),
  )
}

// Icon components
def HomeIcon: FluxusNode = {
  svg(
    xmlns   := "http://www.w3.org/2000/svg",
    cls     := "h-5 w-5",
    fill    := "none",
    viewBox := "0 0 24 24",
    stroke  := "currentColor",
    path(
      strokeLinecap  := "round",
      strokeLinejoin := "round",
      strokeWidth    := "2",
      d := "M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6",
    ),
  )
}

def GridIcon: FluxusNode = {
  svg(
    xmlns   := "http://www.w3.org/2000/svg",
    cls     := "h-5 w-5",
    fill    := "none",
    viewBox := "0 0 24 24",
    stroke  := "currentColor",
    path(
      strokeLinecap  := "round",
      strokeLinejoin := "round",
      strokeWidth    := "2",
      d := "M4 6a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2H6a2 2 0 01-2-2V6zM14 6a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2h-2a2 2 0 01-2-2V6zM4 16a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2H6a2 2 0 01-2-2v-2zM14 16a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2h-2a2 2 0 01-2-2v-2z",
    ),
  )
}

def ComponentsIcon: FluxusNode = {
  svg(
    xmlns   := "http://www.w3.org/2000/svg",
    cls     := "h-5 w-5",
    fill    := "none",
    viewBox := "0 0 24 24",
    stroke  := "currentColor",
    path(
      strokeLinecap  := "round",
      strokeLinejoin := "round",
      strokeWidth    := "2",
      d := "M19 11H5m14 0a2 2 0 012 2v6a2 2 0 01-2 2H5a2 2 0 01-2-2v-6a2 2 0 012-2m14 0V9a2 2 0 00-2-2M5 11V9a2 2 0 012-2m0 0V5a2 2 0 012-2h6a2 2 0 012 2v2M7 7h10",
    ),
  )
}

def GitHubIcon: FluxusNode = {
  svg(
    xmlns   := "http://www.w3.org/2000/svg",
    width   := "20",
    height  := "20",
    viewBox := "0 0 24 24",
    cls     := "fill-current",
    path(
      d := "M12 0c-6.626 0-12 5.373-12 12 0 5.302 3.438 9.8 8.207 11.387.599.111.793-.261.793-.577v-2.234c-3.338.726-4.033-1.416-4.033-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.083-.729.083-.729 1.205.084 1.839 1.237 1.839 1.237 1.07 1.834 2.807 1.304 3.492.997.107-.775.418-1.305.762-1.604-2.665-.305-5.467-1.334-5.467-5.931 0-1.311.469-2.381 1.236-3.221-.124-.303-.535-1.524.117-3.176 0 0 1.008-.322 3.301 1.23.957-.266 1.983-.399 3.003-.404 1.02.005 2.047.138 3.006.404 2.291-1.552 3.297-1.23 3.297-1.23.653 1.653.242 2.874.118 3.176.77.84 1.235 1.911 1.235 3.221 0 4.609-2.807 5.624-5.479 5.921.43.372.823 1.102.823 2.222v3.293c0 .319.192.694.801.576 4.765-1.589 8.199-6.086 8.199-11.386 0-6.627-5.373-12-12-12z",
    ),
  )
}

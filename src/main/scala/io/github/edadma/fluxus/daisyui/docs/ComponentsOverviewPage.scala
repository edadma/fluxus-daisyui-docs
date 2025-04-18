package io.github.edadma.fluxus.daisyui.docs

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.daisyui.*

// Components overview page
val ComponentsOverviewPage = () => {
  // Component categories
  val componentCategories = List(
    (
      "General",
      List(
        ComponentInfo("Button", "Interactive button element", "#button"),
        ComponentInfo("Avatar", "User or item avatars", "#avatar"),
        ComponentInfo("Icon", "Semantic vector graphics", "#icon"),
      ),
    ),
    (
      "Layout",
      List(
        ComponentInfo("Grid", "2D layout system", "#grid"),
        ComponentInfo("Container", "Content wrapper", "#container"),
        ComponentInfo("Divider", "Separation line", "#divider"),
      ),
    ),
    (
      "Navigation",
      List(
        ComponentInfo("Menu", "Application menu", "#menu"),
        ComponentInfo("Tabs", "Content tabs", "#tabs"),
        ComponentInfo("Pagination", "Page navigation", "#pagination"),
      ),
    ),
    (
      "Data Display",
      List(
        ComponentInfo("Table", "Data table", "#table"),
        ComponentInfo("Card", "Content container", "#card"),
        ComponentInfo("Badge", "Status indicator", "#badge"),
      ),
    ),
    (
      "Data Entry",
      List(
        ComponentInfo("Form", "Data collection", "#form"),
        ComponentInfo("Input", "Text field", "#input"),
        ComponentInfo("Select", "Option selector", "#select"),
      ),
    ),
    (
      "Feedback",
      List(
        ComponentInfo("Alert", "Feedback message", "#alert"),
        ComponentInfo("Modal", "Dialog window", "#modal"),
        ComponentInfo("Progress", "Progress indicator", "#progress"),
      ),
    ),
  )

  div(
    h1(cls := "text-3xl font-bold mb-6", "Components Overview"),
    p(
      cls := "text-xl mb-8",
      "fluxus-daisyui provides UI components for building beautiful Scala.js applications with Fluxus and DaisyUI.",
    ),

    // Component categories
    div(
      cls := "space-y-12",
      componentCategories.map { case (category, components) =>
        div(
          cls := "mb-4",
          h2(cls := "text-2xl font-bold mb-4", s"$category"),
          div(
            cls := "grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4",
            components.map(comp => ComponentCard <> comp),
          ),
        )
      },
    ),
  )
}

// Component info model
case class ComponentInfo(
    name: String,
    description: String,
    href: String,
)

// Component card in overview
val ComponentCard = (props: ComponentInfo) => {
  a(
    href := props.href,
    cls  := "block p-4 border rounded-md hover:border-primary hover:shadow-md transition-all",
    div(
      cls := "text-lg font-medium mb-1",
      props.name,
    ),
    div(
      cls := "text-sm opacity-70",
      props.description,
    ),
  )
}

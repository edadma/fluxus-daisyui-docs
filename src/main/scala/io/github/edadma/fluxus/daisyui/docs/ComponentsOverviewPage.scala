package io.github.edadma.fluxus.daisyui.docs

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.daisyui.*

// Components overview page with card layout matching the previous Featured Components section
val ComponentsOverviewPage = () => {
  // Component info for cards
  val components = List(
    ComponentInfo(
      name = "Button",
      description = "Flexible buttons with extensive styling options.",
      href = "#button",
      example = div(
        cls := "flex flex-wrap gap-2",
        Button <> ButtonProps(text = "Primary", variant = "primary"),
        Button <> ButtonProps(text = "Secondary", variant = "secondary"),
        Button <> ButtonProps(text = "Accent", variant = "accent"),
      ),
    ),
    ComponentInfo(
      name = "Table",
      description = "Powerful tables for data display.",
      href = "#table",
      example = div(
        cls := "border rounded overflow-x-auto",
        table(
          cls := "table table-xs",
          thead(
            tr(
              th("ID"),
              th("Name"),
            ),
          ),
          tbody(
            tr(
              td("1"),
              td("John"),
            ),
            tr(
              td("2"),
              td("Jane"),
            ),
          ),
        ),
      ),
    ),
    ComponentInfo(
      name = "Card",
      description = "Versatile content containers.",
      href = "#card",
      example = div(
        cls := "border rounded p-4 bg-base-200",
        div(
          cls := "font-medium",
          "Sample Card",
        ),
        div(
          cls := "text-sm opacity-70 mt-1",
          "This is an example of a card component.",
        ),
      ),
    ),
    ComponentInfo(
      name = "Avatar",
      description = "User or item avatars with various shapes and sizes.",
      href = "#avatar",
      example = div(
        cls := "flex gap-2",
        Avatar <> AvatarProps(
          text = Some("JD"),
          bgClass = "bg-primary",
          textColorClass = "text-primary-content",
        ),
        Avatar <> AvatarProps(
          text = Some("AB"),
          bgClass = "bg-secondary",
          textColorClass = "text-secondary-content",
        ),
      ),
    ),
    ComponentInfo(
      name = "Badge",
      description = "Small status indicators or tags.",
      href = "#badge",
      example = div(
        cls := "flex gap-2",
        Badge <> BadgeProps(text = "New", variant = "primary"),
        Badge <> BadgeProps(text = "Success", variant = "success"),
        Badge <> BadgeProps(text = "Warning", variant = "warning"),
      ),
    ),
    ComponentInfo(
      name = "Alert",
      description = "Notification and feedback messages.",
      href = "#alert",
      example = Alert <> AlertProps(
        variant = "info",
        title = Some("Information"),
        message = Some("This is an informational alert."),
      ),
    ),
    ComponentInfo(
      name = "Input",
      description = "Form input elements with styling options.",
      href = "#input",
      example = Input <> InputProps(
        placeholder = Some("Enter text here..."),
        bordered = true,
      ),
    ),
    ComponentInfo(
      name = "Progress",
      description = "Progress indicators and loading state.",
      href = "#progress",
      example = div(
        Progress <> ProgressProps(
          value = Some(70.0),
          variant = "primary",
        ),
      ),
    ),
    // More components can be added here
  )

  div(
    h1(cls := "text-3xl font-bold mb-6", "Components Overview"),
    p(
      cls := "text-xl mb-8",
      "Browse our collection of UI components built with fluxus-daisyui.",
    ),

    // Component cards in a grid
    div(
      cls := "grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6",
      components.map(comp => ComponentCard <> comp),
    ),
  )
}

// Extended component info model with example
case class ComponentInfo(
    name: String,
    description: String,
    href: String,
    example: FluxusNode,
)

// Component card in overview - styled like the featured components section
val ComponentCard = (props: ComponentInfo) => {
  Card <> CardProps(
    children = div(
      h3(cls := "text-xl font-bold mb-2", props.name),
      p(cls  := "mb-4", props.description),
      div(
        cls := "mb-4",
        props.example,
      ),
      div(
        cls := "mt-4",
        a(
          href := props.href,
          cls  := "btn btn-sm btn-ghost",
          "View Documentation",
        ),
      ),
    ),
  )
}

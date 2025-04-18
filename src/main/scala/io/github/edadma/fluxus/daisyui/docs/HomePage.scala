package io.github.edadma.fluxus.daisyui.docs

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.daisyui.*

// Home page
val HomePage = () => {
  div(
    h1(cls := "text-4xl font-bold mb-6", "Fluxus DaisyUI"),
    p(
      cls := "text-xl mb-6",
      "A comprehensive component library for Fluxus implementing the DaisyUI component system with full Tailwind CSS integration.",
    ),
    div(
      cls := "grid grid-cols-1 md:grid-cols-2 gap-6 mt-8",
      Card <> CardProps(
        title = Some("Getting Started"),
        children = div(
          p("Add the dependency to your build.sbt:"),
          pre(
            cls := "bg-base-300 p-4 rounded mt-2 overflow-auto",
            code("""libraryDependencies += "io.github.edadma" %%% "fluxus-daisyui" % "0.0.4""""),
          ),
          div(
            cls := "mt-4",
            Button <> ButtonProps(
              text = "Installation Guide",
              variant = "primary",
//              href = Some("#installation"), // Would need to add an installation page later
            ),
          ),
        ),
      ),
      Card <> CardProps(
        title = Some("Features"),
        children = div(
          ul(
            cls := "list-disc list-inside space-y-2",
            li("Ready-to-use DaisyUI components"),
            li("Type-safe API with Scala's type system"),
            li("Comprehensive styling options"),
            li("Responsive design support"),
            li("Accessibility features built-in"),
            li("Seamless integration with Fluxus"),
          ),
        ),
      ),
    ),
    h2(cls := "text-2xl font-bold mt-12 mb-4", "Featured Components"),
    div(
      cls := "grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6",

      // Button card
      Card <> CardProps(
        children = div(
          h3(cls := "text-xl font-bold mb-2", "Button"),
          p(cls  := "mb-4", "Flexible buttons with extensive styling options."),
          div(
            cls := "flex flex-wrap gap-2",
            Button <> ButtonProps(text = "Primary", variant = "primary"),
            Button <> ButtonProps(text = "Secondary", variant = "secondary"),
            Button <> ButtonProps(text = "Accent", variant = "accent"),
          ),
          div(
            cls := "mt-4",
            a(
              href := "#button",
              cls  := "btn btn-sm btn-ghost",
              "View Documentation",
            ),
          ),
        ),
      ),

      // Table card
      Card <> CardProps(
        children = div(
          h3(cls := "text-xl font-bold mb-2", "Table"),
          p(cls  := "mb-4", "Powerful tables for data display."),
          div(
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
          div(
            cls := "mt-4",
            a(
              href := "#table",
              cls  := "btn btn-sm btn-ghost",
              "View Documentation",
            ),
          ),
        ),
      ),

      // Card component card
      Card <> CardProps(
        children = div(
          h3(cls := "text-xl font-bold mb-2", "Card"),
          p(cls  := "mb-4", "Versatile content containers."),
          div(
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
          div(
            cls := "mt-4",
            a(
              href := "#card", // Would need to add a Card page later
              cls  := "btn btn-sm btn-ghost",
              "View Documentation",
            ),
          ),
        ),
      ),
    ),
  )
}

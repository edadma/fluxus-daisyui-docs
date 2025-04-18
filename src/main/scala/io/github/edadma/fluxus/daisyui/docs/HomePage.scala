package io.github.edadma.fluxus.daisyui.docs

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.daisyui.*

// Home page without the Featured Components section
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
    div(
      cls := "mt-12 text-center",
      p(
        cls := "mb-4",
        "Explore our complete collection of components and examples.",
      ),
      a(
        href := "#overview",
        cls  := "btn btn-primary",
        "Browse Components",
      ),
    ),
  )
}

package io.github.edadma.fluxus.daisyui.docs

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.daisyui.*

// Not found page
val NotFoundPage = () => {
  div(
    cls := "flex flex-col items-center justify-center py-16",
    div(
      cls := "text-9xl font-bold text-base-100",
      "404",
    ),
    h1(cls := "text-4xl font-bold mt-4 mb-2", "Page Not Found"),
    p(cls  := "text-xl mb-8 opacity-75", "The page you're looking for doesn't exist or has been moved."),
    a(
      href := "#home",
      cls  := "btn btn-primary",
      "Back to Home",
    ),
  )
}

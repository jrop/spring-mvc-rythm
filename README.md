# Rythm Templates for Spring MVC

## Setup

Add the three Java files (`RythmEngineFactory.java`, `SimpleRythmView.java`, and `SimpleRythmViewResolver.java`) to your project under a package `info.jrop.spring.rythm.viewresolver`.

Add this dependency to your `pom.xml`:

    <dependency>
        <groupId>org.rythmengine</groupId>
        <artifactId>rythm-engine</artifactId>
        <version>1.0-b10</version>
    </dependency>

In your `servlet-context.xml`, comment out the bean that initializes the `InternalResourceViewResolver`, and add the following XML:

    <beans:bean class="info.jrop.spring.rythm.viewresolver.SimpleRythmViewResolver">
        <beans:property name="rootDirectory" value="/WEB-INF/views/" />
        <beans:property name="suffix" value=".html" />
    </beans:bean>

You can now create Rythm views in the /WEB-INF/views/ subdirectory (based on the above configuration).

# Example

Say in my `HomeController` I have the following action:

    @RequestMapping(value = "/rythm/{name}", method = RequestMethod.GET)
    public String rythm(Model model, @PathVariable String name) {
        model.addAttribute("name", name);
        return "rythm"; // denotes that the view for this action is named "rythm.html" (or, more generally, "rythm.<suffix>")
    }

Then, I can have two views in the /WEB-INF/views/ folder:

### rythm.html

    @extends(layout)
    @args String name
    Hello, @name!

### layout.html

    <h1>Welcome<h1>
    @render()

In this case, the rendered HTML for a request at `localhost/my-app/rythm/World` would be:

    <h1>Welcome<h1>
    Hello, World!

# Licence

The MIT License (MIT)

Copyright (c) 2014 Jonathan Apodaca

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.

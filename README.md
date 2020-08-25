# Interactive FP interpreter

[![builds.sr.ht status](https://builds.sr.ht/~bbuccianti/fp/.build.yml.svg)](https://builds.sr.ht/~bbuccianti/fp/.build.yml?)

## Overview

An interpreter to play with FP expressions. FP it's a language proposed by John Backus in his paper [Can programming be liberated from the von Neumann style?: a functional style and its algebra of programs](https://dl.acm.org/doi/10.1145/359576.359579). This project was made while I was a [INSPT](http://inspt.utn.edu.ar) student with the guidance and help of Diego Corsi.

## Before You Start

You'll need to install Clojure and Leiningen (a build tool) by following [these instructions](https://purelyfunctional.tv/guide/how-to-install-clojure/).

## Basic usage

Make sure you have the
[latest version of Leiningen installed](https://github.com/technomancy/leiningen#installation).

    lein figwheel

This would make a :dev build and let you play with it on your browser. Default ports are 3449 for server and 7888 for nREPL connection.

## Testing

It's necessary to install ws, in order to run tests with kaocha:

    npm i ws

Run all unit tests with:

    lein kaocha

Most of the times you could run tests with --watch flag in order to re-run tests when a source file it's changed and saved:

    lein kaocha --watch

## Contributing

Contributions are welcome! If you see something wrong with the code or if you think it can be improved in any way, don't hesitate to fix this and send your patch to this [email](mailto:benjamin@buccianti.dev) or, if you prefer, create a Github pull request.

## License

```
The MIT License (MIT)

Copyright © 2020 Benjamín Buccianti

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

package layouts

import graph.Graph

// Belonging "Hardware" blueprint: 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

class Grid4 : Graph() {


}
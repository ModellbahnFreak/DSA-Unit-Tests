
class List {

    SVG_NS = "http://www.w3.org/2000/svg";
    RECT_WIDTH = 50;//30;//50;
    RECT_HEIGHT = 30;
    BORDER_WIDTH = 3;//3;//1.5;
    DISTANCE = 20;//0;//20;
    ARROW_SIZE = 3;
    parent;
    content;
    elements = [];
    currentPointer = 0;
    brackets = [];

    constructor(content, parent, isLinkedFw, isLinkedBw) {
        if (!!parent) {
            this.parent = parent;
        } else {
            this.parent = document.createElement("div");
            document.querySelector("#main").appendChild(this.parent);
        }
        this.content = content;
        this.draw(isLinkedFw, isLinkedBw);
    }

    draw(isLinkedFw, isLinkedBw) {
        while (this.parent.firstElementChild) {
            this.parent.firstElementChild.remove();
        }
        this.elements = [];
        if (isLinkedFw) {
            const arrowLine = document.createElement("i");
            arrowLine.classList.add("horizontalLine");
            arrowLine.style.width = (this.DISTANCE + this.RECT_WIDTH * 0.1) + "px";
            arrowLine.style.left = (-this.BORDER_WIDTH) + "px";
            arrowLine.style.top = (this.RECT_HEIGHT * 0.5) + "px";
            this.parent.appendChild(arrowLine);
            const arrow = document.createElement("i");
            arrow.classList.add("arrow");
            arrow.classList.add("arrowRight");
            arrow.style.top = (this.RECT_HEIGHT * 0.5) + "px";
            arrow.style.left = (this.DISTANCE + this.ARROW_SIZE - this.BORDER_WIDTH) + "px";
            this.parent.appendChild(arrow);
            const circle = document.createElement("i");
            circle.classList.add("circle");
            circle.style.width = (this.RECT_WIDTH * 0.1) + "px";
            circle.style.height = (this.RECT_WIDTH * 0.1) + "px";
            circle.style.top = (this.RECT_HEIGHT * 0.5) + "px";
            circle.style.left = (this.RECT_WIDTH * 0.05 - this.BORDER_WIDTH) + "px";
            circle.style.transform = "translate(-50%, -50%)";
            this.parent.appendChild(circle);
        }
        for (var i = 0; i < this.content.length; i++) {
            const rect = document.createElement("div");
            const span = document.createElement("span");
            const small = document.createElement("small");
            rect.appendChild(span);
            rect.appendChild(small);
            small.innerText = i;
            small.className = "smallIndex";
            span.innerText = this.content[i];
            rect.classList.add("rect");
            var lineRight, arrowLine, arrow, circle;
            if (isLinkedFw) {
                rect.classList.add("linkedListFw");
                lineRight = document.createElement("i");
                lineRight.classList.add("verticalLine");
                lineRight.style.right = "20%";
                rect.appendChild(lineRight);
                arrowLine = document.createElement("i");
                arrowLine.classList.add("horizontalLine");
                arrowLine.style.width = (this.DISTANCE + this.RECT_WIDTH * 0.1) + "px";
                arrowLine.style.right = (-this.DISTANCE - this.BORDER_WIDTH) + "px";
                rect.appendChild(arrowLine);
                arrow = document.createElement("i");
                arrow.classList.add("pointerElement");
                if (i == this.content.length - 1) {
                    this.makeLine(arrow);
                } else {
                    this.makeArrow(arrow);
                }
                rect.appendChild(arrow);
                circle = document.createElement("i");
                circle.classList.add("circle");
                circle.style.width = (this.RECT_WIDTH * 0.1) + "px";
                circle.style.height = (this.RECT_WIDTH * 0.1) + "px";
                rect.appendChild(circle);
            }
            if (isLinkedBw) {
                rect.classList.add("linkedListBw");
            }
            rect.style.width = this.RECT_WIDTH + "px";
            rect.style.height = this.RECT_HEIGHT + "px";
            rect.style.left = (i * (this.RECT_WIDTH + 2 * this.BORDER_WIDTH + this.DISTANCE) + this.DISTANCE + this.RECT_WIDTH * 0.1) + "px";
            this.parent.appendChild(rect);
            this.elements.push({
                value: this.content[i],
                index: i,
                element: rect,
                span: span,
                small: small,
                arrow: arrow
            });
        }
    }

    clearColor() {
        this.elements.forEach(e => {
            e.element.className = "rect";
        });
    }

    get(index, keepColor) {
        if (!keepColor) {
            this.clearColor();
        }
        this.elements[index].element.classList.add("rectRead");
        return this.elements[index].value
    }

    iterator(keepColor) {
        this.currentPointer = -1;
        return this;
    }

    first(keepColor) {
        this.currentPointer = 0;
        return this.this.get(0, keepColor);
    }

    next(keepColor) {
        this.currentPointer++;
        if (this.currentPointer >= this.elements.length) {
            throw new Error("No more elements");
        }
        return this.get(this.currentPointer, keepColor);
    }

    hasNext() {
        return this.currentPointer < this.elements.length - 1;
    }

    hasPrev() {
        return this.currentPointer > 0;
    }

    prev(keepColor) {
        this.currentPointer;
        if (this.currentPointer < 0) {
            throw new Error("No more elements");
        }
        return this.get(this.currentPointer, keepColor);
    }

    set(index, value, keepColor) {
        if (!keepColor) {
            this.clearColor();
        }
        this.elements[index].element.classList.add("rectWrite");
        this.elements[index].value = value;
        this.elements[index].span.innerText = value;
        this.content[index] = value;
    }

    swap(i1, i2, keepColor) {
        if (!keepColor) {
            this.clearColor();
        }
        const e1 = this.elements[i1];
        const e2 = this.elements[i2];
        const e1X = e1.element.style.left;
        e1.element.classList.add("rectMove");
        e2.element.classList.add("rectMove");
        e1.element.style.left = e2.element.style.left;
        e2.element.style.left = e1X;
        e1.index = i2;
        e2.index = i1;
        e1.small.innerText = i2;
        e2.small.innerText = i1;
        this.elements[i1] = e2;
        this.elements[i2] = e1;
        var temp = this.content[i1];
        this.content[i1] = this.content[i2];
        this.content[i2] = temp;
        if (e1.index == this.content.length - 1) {
            this.makeArrow(e2.arrow);
            this.makeLine(e1.arrow);
        } else if (e2.index == this.content.length - 1) {
            this.makeArrow(e1.arrow);
            this.makeLine(e2.arrow);
        }
    }

    length() {
        return this.elements.length;
    }

    makeLine(elem) {
        elem.classList.remove("arrow");
        elem.classList.remove("arrowRight");
        elem.classList.add("verticalLine");
        elem.style.height = (this.ARROW_SIZE * 4) + "px";
        elem.style.top = (this.RECT_HEIGHT * 0.5 - this.ARROW_SIZE * 2) + "px";
        elem.style.right = (-this.DISTANCE - this.BORDER_WIDTH) + "px";
    }

    makeArrow(elem) {
        elem.classList.remove("verticalLine");
        elem.classList.add("arrow");
        elem.classList.add("arrowRight");
        elem.style.height = "";
        elem.style.top = "";
        elem.style.right = (-this.DISTANCE - this.BORDER_WIDTH - this.ARROW_SIZE) + "px";
    }

    addBracket(beforeIndex, opening) {
        const b = document.createElement("div");
        const lbl = document.createElement("small");
        if (opening) {
            b.classList.add("opening");
            lbl.innerText = "u";
        } else {
            b.classList.add("closing");
            b.style.transform = "translateX(" + (-3 * this.BORDER_WIDTH) + "px)";
            lbl.innerText = "o";
        }
        b.style.left = (beforeIndex * (this.RECT_WIDTH + 2 * this.BORDER_WIDTH + this.DISTANCE) + this.DISTANCE + this.RECT_WIDTH * 0.1) + "px";
        b.style.height = (this.RECT_HEIGHT + 2 * this.BORDER_WIDTH) + "px";
        b.style.top = (-this.BORDER_WIDTH) + "px";
        b.style.opacity = 1;
        b.appendChild(lbl);
        lbl.classList.add("smallIndex");
        lbl.classList.add("smallGreen");
        b.classList.add("bracket");
        this.brackets.push(b);
        this.parent.appendChild(b);
    }

    useBracket(beforeIndex, bracketIndex) {
        const b = this.brackets[bracketIndex];
        b.style.opacity = 1;
        b.style.left = (beforeIndex * (this.RECT_WIDTH + 2 * this.BORDER_WIDTH + this.DISTANCE) + this.DISTANCE + this.RECT_WIDTH * 0.1) + "px";
    }

    removeBrackets() {
        this.brackets.forEach(b => {
            b.style.opacity = 0;
        });
    }
}
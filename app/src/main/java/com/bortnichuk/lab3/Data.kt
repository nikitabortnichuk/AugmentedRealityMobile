package com.bortnichuk.lab3
const val htmlString =
    "<!DOCTYPE html>\n" +
            "<html lang=\"en\"><head>\n" +
            "    <title>sievertSurface surface</title>\n" +
            "    <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />\n" +
            "    <meta name=\"viewport\" content=\"width=device-width,user-scalable=no,minimum-scale=1.0,maximum-scale=1.0\" />\n" +
            "</head>\n" +
            "<body>\n" +
            "<script src=\"http://mrdoob.github.io/three.js/build/three.min.js\"></script>\n" +
            "<script src=\"http://mrdoob.github.io/three.js/examples/js/controls/TrackballControls.js\"></script>\n" +
            "<script type=\"text/javascript\">\n" +
            "\n" +
            "    var render, scene, camera, controls;\n" +
            "\tvar geometry, material, meshFilled;\n" +
            "var count = 0;\n" +
            "\t\n" +
            "var x = 0, y = 0, z = 0;\n" +
            "\n" +
            "function setAxis(x_, y_, z_) {\n" +
            "x = x_;\n" +
            "y = y_;\n" +
            "z = z_;\n" +
            "}\n" +
            "    init();\n" +
            "\tanimate();\n" +
            "\n" +
            "    function init() {\n" +
            "        render = new THREE.WebGLRenderer({alpha: 1, antialias: true, clearColor: 0xffffff});\n" +
            "        render.setSize(window.innerWidth, window.innerHeight);\n" +
            "        document.body.appendChild(render.domElement);\n" +
            "        scene = new THREE.Scene();\n" +
            "\n" +
            "        camera = new THREE.PerspectiveCamera(120, window.innerWidth / window.innerHeight, 0.1, 1000);\n" +
            "        camera.position.set(0, 0, 2);\n" +
            "        controls = new THREE.TrackballControls(camera, render.domElement);\n" +
            "\n" +
            "        geometry = new THREE.ParametricGeometry(sievertSurface, 100, 100);\n" +
            "\t\t\n" +
            "        materialFilled = new THREE.MeshNormalMaterial({side: 2, wireframe: false})\n" +
            "\n" +
            "        meshFilled = new THREE.Mesh(geometry, materialFilled);\n" +
            "\n" +
            "        meshFilled.scale.x = meshFilled.scale.y = meshFilled.scale.z = 0.01 \n" +
            "\n" +
            "        scene.add(meshFilled);\n" +
            "    }\n" +
            "\n" +
            "    function sievertSurface(u_, v_, target) {\n" +
            "        const a = 1;\n" +
            "        const w = 60;\n" +
            "        const u = -Math.PI / 2 + u_ * (Math.PI / (100 - a * 2));\n" +
            "        const v = 0.105 + v_ * 1;\n" +
            "        const k = 0.5;\n" +
            "        const p = -u / Math.sqrt(k + 1) + Math.atan(Math.tan(2 * Math.PI * u) * Math.sqrt(k + 1));\n" +
            "        const l = 2 / (k + 1 - k * Math.sin(2 * Math.PI * v) * Math.sin(2 * Math.PI * v) * Math.cos(2 * Math.PI * u) * Math.cos(2 * Math.PI * u));\n" +
            "        const r = (l * Math.sqrt((k + 1) * (1 + k * Math.sin(2 * Math.PI * u) * Math.sin(2 * Math.PI * u))) * Math.sin(2 * Math.PI * v)) / Math.sqrt(k);\n" +
            "        \n" +
            "        const x = r * Math.cos(2 * Math.PI * p);\n" +
            "        const y = r * Math.sin(2 * Math.PI * p);\n" +
            "        const z = (Math.log(Math.tan(2 * Math.PI * v / 2)) + l * (k + 1) * Math.cos(2 * Math.PI * v)) / Math.sqrt(k);\n" +
            "        target.set(x * w, z * w, y * w);\n" +
            "\t}\n" +
            "\n" +
            "    function animate() {\n" +
            "        requestAnimationFrame(animate);\n" +
            "        render.render(scene, camera);\n" +
            "        var euler = new THREE.Euler();\n" +
            "        euler.set(y, z, -x, 'YXZ');\n" +
            "        meshFilled.quaternion.setFromEuler(euler);\n" +
            "        controls.update();\n" +
            "    }\n" +
            "\n" +
            "</script><canvas width=\"468\" height=\"689\" style=\"width: 468px; height: 689px;\"></canvas>\n" +
            "\n" +
            "</body></html>"


<h1>LooqBox Technical Test</h1>
<p>This is an answer for the technical test by LooqBox</p>
<p>This project is a SpringBoot API that consumes PokéAPI</p>
<h2>Diagram and Fluxogram</h2>
<p>Diagram and fluxogram[https://www.figma.com/board/Ne7WdxtUhKdgHJPXJVBqZt/LooqBox-Teste-Erickson-Augusto---Fluxograma?node-id=0-1&t=SV9aBqO4QV5AHZGt-1]</p>
<hr/>
<h1>Explanations</h1>
<p>Here I explain why I chose specific approaches to create this project,
such as why I opted for REST microservices instead of messaging,
why I used Bubble Sort, and other decisions made during development.</p>

<h2>Why REST Microservices Style?</h2> 
<p> I chose the REST microservices style because it's simple, widely used, and easy to implement. 
REST works well for our needs, especially when interacting with external services like the PokéAPI. 
It's straightforward to set up, scale, and test, making it a perfect fit for this project. 
Plus, its stateless nature makes it easy to maintain and expand as the system grows. </p>

<h2>Why Bubble Sort algorithm?</h2>
<p> Both methods implement the Bubble Sort algorithm, which is a simple sorting algorithm. 
 When the algorithm is sorting in reverse order, the worst-case scenario for Big-θ occurs, as it takes the most time, 
leading to the formula O(n²), where n = list.length, since each element must be compared with the rest of the list. 
If the list is already sorted, the best-case scenario occurs; however, the algorithm will still need to traverse the entire list to compare all elements.
The space complexity is O(1), as there is no need for additional memory.

I chose the Bubble Sort system because, although it is not the most efficient, it is the most cohesive for an application that
does not transfer large amounts of data, like this one.
</p>

<h2>Why a DockerCompose and a Dockerfile for each API?</h2>
<p>My machine was encountering some issues with Docker. When I tried to run all three services in the same container, 
their ports were not being properly separated, and instead, all three were trying to use the same port. 
This caused serious issues that made the services unusable. By creating a separate Docker Compose and Dockerfile for each API, 
I was able to isolate the services, ensuring they run smoothly and independently with proper port allocation.</p>

<hr/>
<h2>How to run it!</h2>
<p>To run the project, you have two options: using Maven or Docker.</p>

<h3>Option 1: Running with Maven</h3>
<ol>
    <li>Make sure you have Java 17+ and Maven installed on your system.</li>
    <li>Clone the project repository from GitHub: <code>git clone https://github.com/EricksonLOOP/Looqbox-Challenge.git</code></li>
    <li>Navigate to the API directory you want to run (cache, looqapi, looqapp): <code>cd app/[api-directory]</code></li>
    <li>Build the project using Maven: <code>mvn clean install</code></li>
    <li>Run the project: <code>mvn spring-boot:run</code></li>
    <li>Access the API via <code>http://localhost:8080</code></li> 
</ol>

<h3>Option 2: Running with Docker</h3>
<ol>
    <li>Make sure you have Docker installed on your system.</li>
    <li>Clone the project repository from GitHub: <code>git clone https://github.com/EricksonLOOP/Looqbox-Challenge.git</code></li>
    <li>Navigate to the API directory you want to run (cache, looqapi, looqapp): <code>cd app/[api-directory]</code></li>
    <li>Build and run the Docker container: <code>docker-compose up --build</code></li>
    <li>Access the API via <code>http://localhost:8080</code></li> 
</ol>

<p>Repeat the above steps for each API (cache, looqapi, looqapp) to start all services.</p>
<hr/>
<hr/>
<h2>Endpoints</h2>
<li><b>GET</b> /pokemon</li>
<div>
    <h5>Params</h5>
    <span><pre><b>query: String | Required: false</b></pre></span>
    <span><pre><b>sort: String | Required: false | Variants: "alphabetical" - "length"</b></pre></span>
</div>
<div>
    <h5>Return</h5>
    <div>
   <code><pre>
<b>HTTP STATUS: 200</b>
return:[
List< <b>PokemonDTO</b> >
]
</pre></code>
    </div>
    <div>
   <code><pre>
<b>HTTP STATUS: 500</b>
"Error fetching pokemons" 
</pre></code>
    </div>
</div>

<li><b>GET</b> /pokemons/highlight</li>
<div>
    <h5>Params</h5>
    <span><pre><b>query: String | Required: false</b></pre></span>
    <span><pre><b>sort: String | Required: false | Variants: "alphabetical" - "length"</b></pre></span>
</div>
<div>
    <h5>Return</h5>
    <div>
   <code><pre>
<b>HTTP STATUS: 200</b>
return:[
List< <b>Pokemon</b> >
]
</pre></code>
    </div>
    <div>
   <code><pre>
<b>HTTP STATUS: 500</b>
"Error fetching pokemons" 
</pre></code>
    </div>
</div>

<h2>Main Objects</h2>
<li><b>PokemonDTO</b></li>
<code>
<pre>
    List< String > results
</pre>
</code>
<li><b>Pokemon</b></li>
<code>
<pre>
    String name;
    String highlight;
</pre>
</code>

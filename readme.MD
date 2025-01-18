<h1>LooqBox Technical Test</h1>
<p>This is a answer for the technical test by LooqBox </p>
<p>This project is a SpringBoot API that consume PokéAPI</p>
<h2>Endpoints</h2>
<li><b >GET </b>/pokemon</li>
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
List< PokemonDTO >
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
<li><b >GET </b>/pokemons/highlight/li>
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
List< Pokemon >
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
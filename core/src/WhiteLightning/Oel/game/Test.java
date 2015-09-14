package WhiteLightning.Oel.game;



/*

public class Test extends ApplicationAdapter {
	
	
	OrthographicCamera camera;
	SpriteBatch batch;
	Texture texture;
	BitmapFont font;
	private GameObject gameObject;
	private GameObject gameObject2;
	private float timeHelper;
	private Music music;
	private Sound scream;
	private ShapeRenderer shapeRenderer;
	
	@Override
	public void create () {
		texture = new Texture("badlogic.jpg");
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.GREEN);
		gameObject = new GameObject(texture);
		gameObject2 = new GameObject(texture);
		gameObject2.x=400; gameObject2.y=400;
		
		gameObject.height=256;
		gameObject.width = 256;
		
		gameObject2.height=256;
		gameObject2.width = 256;
		camera = new OrthographicCamera(800,600);
		music = Gdx.audio.newMusic(Gdx.files.internal("win95.mp3"));
		scream = Gdx.audio.newSound(Gdx.files.internal("Wilhelm.ogg"));
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
	
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		
		batch.draw(gameObject.getTexture(), gameObject.x, gameObject.y);
		batch.draw(gameObject2.getTexture(), gameObject2.x, gameObject2.y);

		font.draw(batch, "Asia ma kota!", 50, 50);
		
		 shapeRenderer.begin(ShapeType.Line);
		 shapeRenderer.setColor(1, 1, 0, 1);
		 shapeRenderer.line(0, 0, 200, 200);
		 shapeRenderer.rect(10, 300, 123, 123);
		 shapeRenderer.circle(400, 400, 89);
		 shapeRenderer.end();
		
		batch.end();
		
	}
	
	public void update(){
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		camera.position.set(gameObject.x, gameObject.y,0);
		
		if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){
			camera.zoom += 0.02;
		}
		
		if(Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)){
			camera.zoom -= 0.02;
		}
		
		int speed = 100;
		
		if(Gdx.input.isKeyPressed(Keys.W)){
			gameObject.y +=speed * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			gameObject.y -=speed * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.A)){
			gameObject.x -=speed * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			gameObject.x +=speed * Gdx.graphics.getDeltaTime();
		}

		if(Gdx.input.isKeyPressed(Keys.M)){
			music.play();
		}
		
		if(Gdx.input.isKeyPressed(Keys.N)){
			scream.play(0.2f);
		}
		
		if(Gdx.input.isKeyPressed(Keys.V)){
			
		}
		
		
		if(Gdx.input.isKeyPressed(Keys.X)){
			camera.rotate(0.2f);
		}
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		}
		
		if(gameObject.overlaps(gameObject2)){
			System.out.println("Crash!");
			scream.play(0.2f);

		}
		
		timeHelper += Gdx.graphics.getDeltaTime();
		if(timeHelper > 0.5){

		}
		
		
	}
	
	public void dispose(){
		System.out.println("Closing app.");
		texture.dispose();
		batch.dispose();
		font.dispose();
		music.dispose();
				
	}
	
}
*/
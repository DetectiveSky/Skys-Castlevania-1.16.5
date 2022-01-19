// Made with Blockbench 4.0.3
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

public static class Modelcustom_model extends EntityModel<Entity> {
	private final ModelRenderer bb_main;
	private final ModelRenderer bladetip_r1;

	public Modelcustom_model() {
		textureWidth = 32;
		textureHeight = 32;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);

		bladetip_r1 = new ModelRenderer(this);
		bladetip_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bb_main.addChild(bladetip_r1);
		setRotationAngle(bladetip_r1, 0.0F, 1.5708F, 1.5708F);
		bladetip_r1.setTextureOffset(9, 5).addBox(-1.0F, -2.0F, -8.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		bladetip_r1.setTextureOffset(0, 18).addBox(-1.0F, -1.0F, -6.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);
		bladetip_r1.setTextureOffset(0, 8).addBox(-1.0F, -2.0F, -6.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);
		bladetip_r1.setTextureOffset(10, 2).addBox(-1.0F, -2.0F, 3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bladetip_r1.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, 4.0F, 3.0F, 6.0F, 1.0F, 0.0F, false);
		bladetip_r1.setTextureOffset(8, 0).addBox(-1.0F, -2.0F, 5.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}
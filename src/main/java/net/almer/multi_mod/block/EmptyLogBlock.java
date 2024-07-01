package net.almer.multi_mod.block;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

import java.util.stream.Stream;

public class EmptyLogBlock extends PillarBlock implements Waterloggable{
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public EmptyLogBlock(Settings settings) {
        super(settings);
        this.setDefaultState(getDefaultState()
                .with(AXIS, Direction.Axis.Y)
                .with(WATERLOGGED, false));
    }
    private static final VoxelShape SHAPE_VERTICAL = VoxelShapes.union(
            VoxelShapes.cuboid(-2.220446049250313e-16, 0, -3.885780586188048e-16, 0.12500000000000044, 1, 0.9999999999999989),
            VoxelShapes.cuboid(0.8749999999999996, 0, -3.885780586188048e-16, 1.0000000000000004, 1, 0.9999999999999989),
            VoxelShapes.cuboid(0.12500000000000022, 0, -3.885780586188048e-16, 0.875, 1, 0.12499999999999989),
            VoxelShapes.cuboid(0.12500000000000022, 0, 0.8749999999999998, 0.875, 1, 0.9999999999999998)
    );
    private static final VoxelShape SHAPE_HORIZONTAL_N = VoxelShapes.union(
            VoxelShapes.cuboid(-2.220446049250313e-16, -2.220446049250313e-16, -1.1102230246251565e-16, 0.12500000000000044, 0.9999999999999991, 0.9999999999999998),
            VoxelShapes.cuboid(0.8749999999999996, -2.220446049250313e-16, -1.1102230246251565e-16, 1.0000000000000004, 0.9999999999999991, 0.9999999999999998),
            VoxelShapes.cuboid(0.12500000000000022, -2.220446049250313e-16, -1.1102230246251565e-16, 0.875, 0.12500000000000006, 0.9999999999999998),
            VoxelShapes.cuboid(0.12500000000000022, 0.875, -1.1102230246251565e-16, 0.875, 1, 0.9999999999999998)
    );
    private static final VoxelShape SHAPE_HORIZONTAL_W = VoxelShapes.union(
            VoxelShapes.cuboid(5.551115123125783e-17, -2.220446049250313e-16, 0.8749999999999993, 1, 0.9999999999999991, 1),
            VoxelShapes.cuboid(5.551115123125783e-17, -2.220446049250313e-16, -6.106226635438361e-16, 1, 0.9999999999999991, 0.12500000000000033),
            VoxelShapes.cuboid(5.551115123125783e-17, -2.220446049250313e-16, 0.12499999999999989, 1, 0.12500000000000006, 0.8749999999999996),
            VoxelShapes.cuboid(5.551115123125783e-17, 0.875, 0.12499999999999989, 1, 1, 0.8749999999999996)
    );
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch(state.get(AXIS)){
            case Z:
                return SHAPE_HORIZONTAL_N;
            case X:
                return SHAPE_HORIZONTAL_W;
            case Y:
                return SHAPE_VERTICAL;
            default:
                return SHAPE_VERTICAL;
        }
    }
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(stack.isIn(ItemTags.AXES)){
            world.emitGameEvent((Entity)player, GameEvent.BLOCK_CHANGE, pos.toCenterPos());
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            player.swingHand(hand);
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(WATERLOGGED);
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
    }
    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
}

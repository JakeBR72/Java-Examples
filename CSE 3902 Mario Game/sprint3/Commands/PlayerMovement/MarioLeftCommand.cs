﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Xna.Framework;

namespace MarioGame
{
    public class MarioLeftCommand : ICommand
    {
        private Game1 Game;
        private MarioStateMachine.MarioState currentState = new MarioStateMachine.MarioState();

        public MarioLeftCommand(Game1 s2)
        {
            this.Game = s2;
        }

        public void Execute()
        {
            currentState = Game.Mario.State();
            if (Game.Mario.Health() == MarioStateMachine.MarioHealth.Dead 
                || MarioStateMachine.MarioState.Ducking == currentState)
            {
                return;
            }
            bool FacingLeft = Game.Mario.FacingLeft();

            if (!FacingLeft)
            {
                Game.Mario.SetLeft();
                if (currentState != MarioStateMachine.MarioState.Jumping)
                {
                    Game.Mario.Idle();
                }

            }
            else if (currentState == MarioStateMachine.MarioState.Idle)
            {
                Game.Mario.Run();
            }
            else
            {
                if (Game.Mario.Physics.XVelocity > -PhysicsUtilites.PlayerWalkVelocity)
                {
                    Game.Mario.Physics.XVelocity -= PhysicsUtilites.PlayerXVelocityIncStep;
                }
                else
                {
                    Game.Mario.Physics.XVelocity = -PhysicsUtilites.PlayerWalkVelocity;
                }
            }
        }
    }
}
